<script>
import { Doughnut } from "vue-chartjs";
import { Chart } from "chart.js";

export default {
  extends: Doughnut,
  props: {
    chartData: {
      type: Chart.Doughnut.chartData
    }
  },
  data() {
    return {
      options: {
        responsive: true,
        maintainAspectRatio: false,
        cutoutPercentage: 60,
        animation: {
          animationRotate: true,
          duration: 2000
        },
        legend: {
          display: false
        },
        tooltips: {
          enabled: false
        }
      }
    };
  },
  methods: {
    update() {
      this.$data._chart.update();
    }
  },
  mounted() {
    this.renderChart(this.chartData, this.options);
  }
};
</script>

<style lang="scss">
#doughnut-chart {
  max-width: 8rem;
  max-height: 8rem;
}
</style>
